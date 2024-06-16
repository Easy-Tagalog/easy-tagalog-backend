from django.shortcuts import render
from rest_framework import status
from rest_framework.decorators import api_view, authentication_classes, permission_classes
from rest_framework.response import Response
from .models import Word
from .serializers import WordSerializer

# These are basically the controllers


@api_view(['GET'])
@authentication_classes([])
@permission_classes([])
def words_list(request):
    words = Word.objects.all()
    serializer = WordSerializer(words, many=True)

    return Response({
        'data': serializer.data
    })
