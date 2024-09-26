from django.urls import path, include
from . import views
from views import get_users
urlpatterns = [
    path('',views.homepage),
    path('users/',get_users,name='get_user'),
]