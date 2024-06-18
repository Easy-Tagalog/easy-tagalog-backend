from rest_framework import generics, filters
from rest_framework.permissions import AllowAny, IsAuthenticated, IsAdminUser
from .models import Word
from .serializers import WordsSerializer


# SECOND IMPLEMENTATION: CLASS BASED VIEWS WITH GENERICS
class WordListCreate(generics.ListCreateAPIView):
    """
    Handles listing and creating words.
    - GET: Public access to list all words
    - GET SEARCH: Public access to all words related to search param
    - POST: Restricted to authenticated admin users
    """
    queryset = Word.objects.all()
    serializer_class = WordsSerializer

    filter_backends = [filters.SearchFilter]
    search_fields = ['tagalog', 'english']

    def get_permissions(self):
        if self.request.method == 'POST':
            return [IsAuthenticated(), IsAdminUser()]
        else:
            return [AllowAny()]


class WordRetrieveUpdateDestroy(generics.RetrieveUpdateDestroyAPIView):
    """
    Handles retrieving, updating, and destorying words by primary key
    - GET: Public access to retrieve word
    - PUT, PATCH, DELETE: Restricted to authenticated admin users
    """
    queryset = Word.objects.all()
    serializer_class = WordsSerializer

    def get_permissions(self):
        if self.request.method != 'GET':
            return [IsAuthenticated(), IsAdminUser()]
        else:
            return [AllowAny()]
