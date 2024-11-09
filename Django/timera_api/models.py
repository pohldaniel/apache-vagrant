from django.db import models
from django.contrib.auth.models import AbstractBaseUser
from django.contrib.auth.models import PermissionsMixin
from django.contrib.auth.base_user import BaseUserManager
from django.utils.translation import gettext_lazy as _

# Create your models here.
class Person(models.Model):
    id = models.CharField(primary_key=True, max_length=255)
    external_company = models.CharField(max_length=255, blank=True, null=True)
    mail = models.CharField(max_length=255, blank=True, null=True)
    password_hash = models.CharField(max_length=255, blank=True, null=True)
    password_reset_token = models.CharField(max_length=255, blank=True, null=True)
    password_reset_token_expiry_date = models.DateTimeField(blank=True, null=True)
    prename = models.CharField(max_length=255, blank=True, null=True)
    surname = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'person'

class Project(models.Model):
    id = models.CharField(primary_key=True, max_length=255)
    company = models.CharField(max_length=255, blank=True, null=True)
    created_at = models.DateTimeField()
    end_date = models.DateField(blank=True, null=True)
    location = models.CharField(max_length=255, blank=True, null=True)
    modified_at = models.DateTimeField(blank=True, null=True)
    start_date = models.DateField(blank=True, null=True)
    status = models.CharField(max_length=8, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'project'

class Timesheet(models.Model):

    class Category(models.TextChoices):
        ILLNESS = "ILLNESS", _("ILLNESS")
        TRAVELTIME = "TRAVELTIME", _("TRAVELTIME")
        VACATION = "VACATION", _("VACATION")
        WORKTIME = "WORKTIME", _("WORKTIME")

    id = models.BigIntegerField(primary_key=True)
    assigned_person = models.CharField(max_length=255, blank=True, null=True)
    assigned_project = models.CharField(max_length=255, blank=True, null=True)
    category = models.CharField(max_length=10, choices=Category.choices,default=Category.WORKTIME)
    difference = models.IntegerField()
    end_time = models.DateTimeField(blank=True, null=True)
    start_time = models.DateTimeField()
    summary = models.IntegerField()
    task = models.CharField(max_length=255, blank=True, null=True)
    person = models.ForeignKey(Person, models.DO_NOTHING, blank=True, null=True)
    project = models.ForeignKey(Project, models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'timesheet'