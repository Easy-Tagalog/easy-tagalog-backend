# Generated by Django 5.0.6 on 2024-06-21 23:54

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('words', '0005_word_note'),
    ]

    operations = [
        migrations.AlterField(
            model_name='word',
            name='note',
            field=models.CharField(blank=True, max_length=100),
        ),
    ]