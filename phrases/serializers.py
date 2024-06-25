from rest_framework import serializers
from .models import Phrase, PhraseWord
from words.models import Word, Conjugation
from words.serializers import WordSerializer, ConjugationSerializer


class PhraseWordSerializer(serializers.ModelSerializer):
    """
    """
    word_id = serializers.UUIDField()
    conjugation_id = serializers.UUIDField(required=False, allow_null=True)

    class Meta:
        model = PhraseWord
        fields = ['word_id', 'conjugation_id',  'order']

    def validate(self, attrs):
        word_id = attrs.get('word_id', None)
        conjugation_id = attrs.get('conjugation_id', None)

        if not word_id and not conjugation_id:
            raise serializers.ValidationError(
                'Must include either a word_id or conjugation_id')

        if word_id and conjugation_id:
            raise serializers.ValidationError(
                "There should either be one word or one conjugation, not both")

        return attrs


class PhraseSerializer(serializers.ModelSerializer):
    phrase_words = PhraseWordSerializer(many=True, required=True)

    class Meta:
        model = Phrase
        fields = [
            'id',
            'english',
            'tagalog',
            'is_question',
            'audio_url',
            'phrase_words'
        ]

    def create(self, validated_data):
        phrase_words_data = validated_data.pop('phrase_words')
        phrase = Phrase.objects.create(**validated_data)

        for phrase_word_data in phrase_words_data:
            word_id = phrase_word_data.pop('word_id', None)
            word = Word.objects.get(id=word_id)

            conjugation_id = phrase_word_data.pop('conjugation_id', None)
            conjugation = Conjugation.objects.get(
                id=conjugation_id) if conjugation_id else None

            PhraseWord.objects.create(
                phrase=phrase, word=word, conjugation=conjugation, **phrase_word_data)

        return phrase
