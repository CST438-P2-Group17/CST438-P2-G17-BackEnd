from django.urls import path, include
from . import views
from .views import get_users, create_user, user_detail
urlpatterns = [
    path('',views.homepage),
    path('users/',get_users,name='get_user'),
    path('users/create/',create_user,name='create_user'),
    path('users/<int:pk>/',user_detail,name='user_detail')
    
]