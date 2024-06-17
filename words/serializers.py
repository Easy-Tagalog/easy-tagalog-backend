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
        ]


class WordsSerializer(serializers.ModelSerializer):
    """
    """
    conjugations = ConjugationsListSerializer(many=True, required=False)

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

    def create(self, validated_data):
        conjugations_data = validated_data.pop('conjugations', [])
        word = Word.objects.create(**validated_data)

        for conjugation_data in conjugations_data:
            Conjugation.objects.create(word=word, **conjugation_data)

        return word

    def validate(self, attrs):
        part_of_speech = attrs.get('part_of_speech')
        conjugations = attrs.get('conjugations', [])

        # Additional checks for verbs
        if part_of_speech == Word.VERB:
        
            # Check if theres a conjugation field
            if not conjugations:
                raise serializers.ValidationError({
                    'conjugations': 'This field is required for verbs'
                })
        
            # Check if conjugations has 3
            if len(conjugations) != 3:
                raise serializers.ValidationError({
                    'conjugations': 'There must be exactly 3 items in conjugations'
                })
            
            # Lastly check that theres a present, future, and past
            present, past, present = False, False, False

            for c in conjugations:
                match(c.get('tense')):
                    case Conjugation.PRESENT:
                        present = True

                    case Conjugation.PAST:
                        past = True

                    case Conjugation.FUTURE:
                        future = True

            if not present or not past or not future:
                raise serializers.ValidationError({
                    'conjugations': 'There must be exactly one present, past, and future tense'
                })

        return attrs
