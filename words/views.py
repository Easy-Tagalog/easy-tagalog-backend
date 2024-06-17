from rest_framework import status
from rest_framework.decorators import api_view, authentication_classes, permission_classes
from rest_framework.response import Response
from .models import Word
from .serializers import WordsSerializer

# These are basically the controllers


@api_view(['GET', 'POST'])
@authentication_classes([])
@permission_classes([])
def words_list(request):
    """
    """

    if request.method == 'GET':
        words = Word.objects.all()
        serializer = WordsSerializer(words, many=True)

        return Response({
            'data': serializer.data
        }, status=status.HTTP_200_OK)
    
    elif request.method == 'POST':
        # Checks if one or if multiple words are being created
        is_many = isinstance(request.data, list)
        serializer = WordsSerializer(data=request.data, many=is_many)

        if serializer.is_valid():
            serializer.save()
            return Response({
                    'data': serializer.data 
                }, status=status.HTTP_201_CREATED)
        
        # Error
        return Response({
                'data': serializer.errors
            }, status=status.HTTP_400_BAD_REQUEST)


@api_view(['GET'])
@authentication_classes([])
@permission_classes([])
def word_detail(request, pk):
    """
    """

    # First check if input primary key exists for a word
    try:
        word = Word.objects.get(pk=pk)
    except Word.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = WordsSerializer(word)

        return Response({
            'data': serializer.data
        }, status=status.HTTP_200_OK)