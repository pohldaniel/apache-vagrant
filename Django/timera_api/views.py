from django.shortcuts import render

from rest_framework import viewsets
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from rest_framework.authentication import TokenAuthentication
from rest_framework import filters
from rest_framework.authtoken.serializers import AuthTokenSerializer
from rest_framework.authtoken.views import ObtainAuthToken
from rest_framework.authtoken.models import Token

from . import serializers
from . import models
from . import permissions

# Create your views here.
class TimesheetViewSet(viewsets.ModelViewSet):
    """Handels creating, reading and updating profiles"""

    serializer_class = serializers.TimesheetSerializer
    queryset = models.Timesheet.objects.all()
   
class CustomAuthToken(ObtainAuthToken):

    def post(self, request, *args, **kwargs):
        serializer = self.serializer_class(data=request.data,
                                           context={'request': request})
        serializer.is_valid(raise_exception=True)
        user = serializer.validated_data['user']
        [token, created] = Token.objects.get_or_create(user=user)
        return Response({
            'token': token.key,
        #    'user_id': user.pk,
        #    'email': user.email
        })

class LoginViewSet(viewsets.ViewSet):
    """Checks email and password and returns an auth token."""

    serializer_class = AuthTokenSerializer

    def create(self, request):
        """Use the ObtainAuthToken APIView to validate and create a token."""

        return CustomAuthToken().post(request=request)