import uuid
from django.db import models
from django.contrib.postgres.fields import ArrayField

# Longest word in Tagalog is 32 characters
# For models, the "id" is already generated

MAX_WORD_LENGTH = 35
MAX_WORD_AUDIO_LENGTH = MAX_WORD_LENGTH + 10

class Word(models.Model):
    NOUN = "N"
    VERB = "V"
    ADJECTIVE = "ADJ"
    ADVERB = "ADV"
    PRONOUN = "PRON"
    PREPOSITION = "PREP"
    CONJUNCTION = "CONJ"
    INTERJECTION = "INTERJ"
    INTERROGROGATIVE = "INTERROG"
    ARTICLE = "ART"
    PARTICLE = "PART"

    PART_OF_SPEECH_CHOICES = (
        (NOUN, "noun"),
        (VERB, "verb"),
        (ADJECTIVE, "adjective"),
        (ADVERB, "adverb"),
        (PRONOUN, "pronoun"),
        (PREPOSITION, "preposition"),
        (CONJUNCTION, "conjunction"),
        (INTERJECTION, "interjection"),
        (INTERROGROGATIVE, "interrogative"),
        (ARTICLE, "article"),
        (PARTICLE, "particle")
    )

    id = models.UUIDField(
        primary_key=True, default=uuid.uuid4, editable=False)

    english = ArrayField(models.CharField(
        max_length=MAX_WORD_LENGTH, blank=False))
    tagalog = models.CharField(max_length=MAX_WORD_LENGTH, blank=False)

    root = models.CharField(max_length=MAX_WORD_LENGTH, blank=False)

    accents = ArrayField(models.IntegerField())

    audio_url = models.CharField(max_length=MAX_WORD_AUDIO_LENGTH, unique=True, blank=False)

    part_of_speech = models.CharField(
        choices=PART_OF_SPEECH_CHOICES, blank=False, max_length=14)
    is_irregular_verb = models.BooleanField(
        blank=True, default=False)

    def is_verb(self) -> bool:
        return self.part_of_speech == self.VERB

    def __str__(self) -> str:
        return self.tagalog


class Conjugation(models.Model):
    PAST = "PAST"
    PRESENT = "PRES"
    FUTURE = "FUT"

    TENSE_CHOICES = (
        ("PAST", "past"),
        ("PRES", "present"),
        ("FUT", "future")
    )

    id = models.UUIDField(
        primary_key=True, default=uuid.uuid4, editable=False)

    tense = models.CharField(max_length=7, choices=TENSE_CHOICES)

    english = models.CharField(
        max_length=MAX_WORD_LENGTH, blank=False)
    tagalog = models.CharField(
        max_length=MAX_WORD_LENGTH, blank=False)

    audio_url = models.CharField(max_length=MAX_WORD_AUDIO_LENGTH, unique=True, blank=False)

    # If verb word is deleted, all conjugations are deleted as well
    word = models.ForeignKey(
        to=Word, on_delete=models.CASCADE, related_name='conjugations')

    def __str__(self) -> str:
        return self.tagalog
