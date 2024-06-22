from django.urls import path
from .views import PhraseListCreate

urlpatterns = [
    path('', PhraseListCreate.as_view(), name='phrase-list-create'),
    # path('<pk>/', )
]
