from django.urls import path, re_path

from .views import WordRetrieveUpdateDestroy, WordListCreate

urlpatterns = [
    path('', WordListCreate.as_view(), name='word-list-create'),
    path('<pk>/', WordRetrieveUpdateDestroy.as_view(),
         name='word-retrieve-update-destroy'),
]
