from django.urls import re_path 
from django.urls import include

from rest_framework.routers import DefaultRouter

from . import views

router = DefaultRouter()
router.register('timesheets', views.TimesheetViewSet)
router.register('login', views.LoginViewSet, basename='login')

urlpatterns = [
    re_path(r'', include(router.urls))
]