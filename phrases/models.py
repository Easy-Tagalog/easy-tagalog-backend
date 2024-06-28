import uuid
from django.db import models

from words.models import Word, Conjugation

MAX_PHRASE_LENGTH = 100
MAX_PHRASE_AUDIO_LENGTH = MAX_PHRASE_LENGTH + 40


class Phrase(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)

    english = models.CharField(max_length=MAX_PHRASE_LENGTH, blank=False)
    tagalog = models.CharField(max_length=MAX_PHRASE_LENGTH, blank=False)

    is_question = models.BooleanField(default=False, blank=True)

    audio_url = models.CharField(
        max_length=MAX_PHRASE_AUDIO_LENGTH, unique=True, blank=False)

    def __str__(self) -> str:
        return self.tagalog


class PhraseWord(models.Model):
    phrase = models.ForeignKey(
        to=Phrase, on_delete=models.CASCADE, related_name='phrase_words')

    word = models.ForeignKey(
        to=Word, null=True, blank=True, on_delete=models.CASCADE)
    conjugation = models.ForeignKey(
        to=Conjugation, null=True, blank=True, on_delete=models.CASCADE)

    order = models.PositiveIntegerField()

    class Meta:
        # Check for duplicate words for a phrase
        constraints = [
            models.UniqueConstraint(
                fields=['phrase', 'word', 'order'], name='unique_phrase_word_order'
            )
        ]

        # Ordering in ascending to match with phrase order
        ordering = ['order']

    def __str__(self) -> str:
        return f"{self.phrase.tagalog} - {self.word.tagalog}"
