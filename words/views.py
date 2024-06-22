from rest_framework import generics, filters, status
from rest_framework.response import Response
from rest_framework.permissions import AllowAny, IsAuthenticated, IsAdminUser
from .models import Word
from .serializers import WordSerializer


class WordListCreate(generics.ListCreateAPIView):
    """
    Handles listing and creating words.
    - GET: Public access to list all words
    - GET SEARCH: Public access to all words related to search param
    - POST: Restricted to authenticated admin users
    """
    queryset = Word.objects.all()
    serializer_class = WordSerializer

    filter_backends = [filters.SearchFilter]
    search_fields = ['tagalog', 'english']

    def create(self, request, *args, **kwargs):
        # Overwrote the create function to take in an array of objects
        if isinstance(request.data, list):
            serializer = self.get_serializer(data=request.data, many=True)
        else:
            serializer = self.get_serializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        self.perform_create(serializer)
        headers = self.get_success_headers(serializer.data)
        return Response(serializer.data, status=status.HTTP_201_CREATED, headers=headers)

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
    serializer_class = WordSerializer

    def get_permissions(self):
        if self.request.method != 'GET':
            return [IsAuthenticated(), IsAdminUser()]
        else:
            return [AllowAny()]
