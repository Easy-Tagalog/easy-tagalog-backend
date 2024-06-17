from django.urls import path, re_path

from .views import words_list, word_detail

urlpatterns = [
    path('', words_list, name='words_list'),
    path('<pk>/', word_detail, name='word_detail')
]
