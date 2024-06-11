from django.db import models
from django.contrib.postgres.fields import ArrayField

# Longest word in Tagalog is 32 characters


class WordBase(models.Model):
    english = models.CharField(max_length=35, blank=False)
    tagalog = models.CharField(max_length=35, blank=False)
    root = models.CharField(max_length=35, blank=False)
    accents = ArrayField(models.IntegerField())
    audio_url = models.URLField(unique=True, default=f"{tagalog}.mp3")


class Word(WordBase):
    PART_OF_SPEECH_CHOICES = {
        "N": "noun",
        "V": "verb",
        "ADJ": "adjective",
        "ADV": "adverb",
        "PRON": "pronoun",
        "PREP": "preposition",
        "CONJ": "conjunction",
        "INTERJ": "interjection",
        "INTERROG": "interrogative",
        "ART": "article",
        "PART": "particle"
    }
    part_of_speech = models.CharField(choices=PART_OF_SPEECH_CHOICES, blank=False, max_length=14)


class Conjugation(WordBase):
    TENSE_CHOICES = {
        "PAST": "past",
        "PRES": "present",
        "FUT": "future"
    }