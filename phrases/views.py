from rest_framework import generics
from rest_framework.permissions import AllowAny, IsAuthenticated, IsAdminUser
from .models import Phrase
from .serializers import PhraseSerializer


class PhraseListCreate(generics.ListCreateAPIView):
    """
    Handles listing and creating phrases.
    - GET: Public access to list all phrases
    - POST: Restricted to authenticated admin users
    """
    queryset = Phrase.objects.all()
    serializer_class = PhraseSerializer

    def get_permissions(self):
        if self.request.method == 'POST':
            return [IsAuthenticated(), IsAdminUser()]
        else:
            return [AllowAny()]
