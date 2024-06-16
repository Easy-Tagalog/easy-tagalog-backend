from rest_framework import serializers
from .models import Word, Conjugation

# Serializers are for taking a model and converting it to JSON compatible data


class ConjugationsListSerializer(serializers.ModelSerializer):
    """
    """
    class Meta:
        model = Conjugation
        fields = [
            'id',
            'tense',
            'english',
            'tagalog',
            'audio_url',
            'word'
        ]


class WordsListSerializer(serializers.ModelSerializer):
    """
    """
    conjugations = ConjugationsListSerializer(many=True)

    class Meta:
        model = Word
        fields = [
            'id',
            'english',
            'tagalog',
            'root',
            'accents',
            'audio_url',
            'part_of_speech',
            'is_irregular_verb',
            'conjugations'
        ]
