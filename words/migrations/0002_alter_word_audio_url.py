# Generated by Django 5.0.6 on 2024-06-16 22:45

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('words', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='word',
            name='audio_url',
            field=models.CharField(max_length=45, unique=True),
        ),
    ]