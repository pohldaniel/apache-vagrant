PRODUCTION
# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Address(models.Model):
    city = models.CharField(max_length=255, blank=True, null=True)
    house_number = models.CharField(max_length=255, blank=True, null=True)
    street = models.CharField(max_length=255, blank=True, null=True)
    zip_code = models.CharField(max_length=255, blank=True, null=True)
    id = models.OneToOneField('Person', models.DO_NOTHING, db_column='id', primary_key=True)

    class Meta:
        managed = False
        db_table = 'address'


class AuthGroup(models.Model):
    name = models.CharField(unique=True, max_length=150)

    class Meta:
        managed = False
        db_table = 'auth_group'


class AuthGroupPermissions(models.Model):
    id = models.BigAutoField(primary_key=True)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)
    permission = models.ForeignKey('AuthPermission', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_group_permissions'
        unique_together = (('group', 'permission'),)


class AuthPermission(models.Model):
    name = models.CharField(max_length=255)
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING)
    codename = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'auth_permission'
        unique_together = (('content_type', 'codename'),)


class AuthtokenToken(models.Model):
    key = models.CharField(primary_key=True, max_length=40)
    created = models.DateTimeField()
    user = models.OneToOneField('ProfilesApiUserprofile', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'authtoken_token'


class Communication(models.Model):
    email = models.CharField(max_length=255, blank=True, null=True)
    phone_private = models.CharField(max_length=255, blank=True, null=True)
    phone_work = models.CharField(max_length=255, blank=True, null=True)
    id = models.OneToOneField('Person', models.DO_NOTHING, db_column='id', primary_key=True)

    class Meta:
        managed = False
        db_table = 'communication'


class ContractCondition(models.Model):
    id = models.BigIntegerField(primary_key=True)
    assigned_person = models.CharField(max_length=255, blank=True, null=True)
    created_at = models.DateTimeField()
    current = models.TextField()  # This field type is a guess.
    cutoff_date = models.DateField(blank=True, null=True)
    cutoff_date_end = models.DateField(blank=True, null=True)
    cutoff_manhours = models.FloatField()
    cutoff_vacation_days = models.IntegerField()
    manhours_per_week = models.FloatField()
    manhours_subtraction_per_month = models.IntegerField()
    modified_at = models.DateTimeField(blank=True, null=True)
    traveltime_subtraction_in_hours = models.FloatField()
    vacation_days_per_year = models.IntegerField()
    person = models.ForeignKey('Person', models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'contract_condition'


class DjangoAdminLog(models.Model):
    action_time = models.DateTimeField()
    object_id = models.TextField(blank=True, null=True)
    object_repr = models.CharField(max_length=200)
    action_flag = models.PositiveSmallIntegerField()
    change_message = models.TextField()
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING, blank=True, null=True)
    user = models.ForeignKey('ProfilesApiUserprofile', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'django_admin_log'


class DjangoContentType(models.Model):
    app_label = models.CharField(max_length=100)
    model = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'django_content_type'
        unique_together = (('app_label', 'model'),)


class DjangoMigrations(models.Model):
    id = models.BigAutoField(primary_key=True)
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'


class DjangoSession(models.Model):
    session_key = models.CharField(primary_key=True, max_length=40)
    session_data = models.TextField()
    expire_date = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_session'


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


class Profile(models.Model):
    birthday = models.DateField(blank=True, null=True)
    locale = models.CharField(max_length=255, blank=True, null=True)
    member_since = models.DateTimeField(blank=True, null=True)
    id = models.OneToOneField(Person, models.DO_NOTHING, db_column='id', primary_key=True)

    class Meta:
        managed = False
        db_table = 'profile'


class ProfilesApiTimesheet(models.Model):
    id = models.BigIntegerField(primary_key=True)
    assigned_person = models.CharField(max_length=255)
    assigned_project = models.DateField()
    category = models.CharField(max_length=1)
    difference = models.IntegerField()
    end_time = models.DateTimeField()
    start_time = models.DateTimeField()
    summary = models.IntegerField()
    task = models.CharField(max_length=255)
    person_id = models.CharField(max_length=255)
    project_id = models.CharField(max_length=255)

    class Meta:
        managed = False
        db_table = 'profiles_api_timesheet'


class ProfilesApiUserprofile(models.Model):
    id = models.BigAutoField(primary_key=True)
    password = models.CharField(max_length=128)
    last_login = models.DateTimeField(blank=True, null=True)
    is_superuser = models.IntegerField()
    email = models.CharField(unique=True, max_length=255)
    name = models.CharField(max_length=255)
    is_active = models.IntegerField()
    is_staff = models.IntegerField()

    class Meta:
        managed = False
        db_table = 'profiles_api_userprofile'


class ProfilesApiUserprofileGroups(models.Model):
    id = models.BigAutoField(primary_key=True)
    userprofile = models.ForeignKey(ProfilesApiUserprofile, models.DO_NOTHING)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'profiles_api_userprofile_groups'
        unique_together = (('userprofile', 'group'),)


class ProfilesApiUserprofileUserPermissions(models.Model):
    id = models.BigAutoField(primary_key=True)
    userprofile = models.ForeignKey(ProfilesApiUserprofile, models.DO_NOTHING)
    permission = models.ForeignKey(AuthPermission, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'profiles_api_userprofile_user_permissions'
        unique_together = (('userprofile', 'permission'),)


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


class ProjectPerson(models.Model):
    project = models.OneToOneField(Project, models.DO_NOTHING, primary_key=True)  # The composite primary key (project_id, person_id) found, that is not supported. The first column is selected.
    person = models.ForeignKey(Person, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'project_person'
        unique_together = (('project', 'person'),)


class Role(models.Model):
    person = models.OneToOneField(Person, models.DO_NOTHING, primary_key=True)  # The composite primary key (person_id, role) found, that is not supported. The first column is selected.
    role = models.CharField(max_length=17)

    class Meta:
        managed = False
        db_table = 'role'
        unique_together = (('person', 'role'),)


class TechnicalKey(models.Model):
    primary_key_name = models.CharField(primary_key=True, max_length=255)
    current_value = models.BigIntegerField()

    class Meta:
        managed = False
        db_table = 'technical_key'


class Timesheet(models.Model):
    id = models.BigIntegerField(primary_key=True)
    assigned_person = models.CharField(max_length=255, blank=True, null=True)
    assigned_project = models.CharField(max_length=255, blank=True, null=True)
    category = models.CharField(max_length=10, blank=True, null=True)
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


class Vacation(models.Model):
    id = models.BigIntegerField(primary_key=True)
    actual_vacation_days = models.IntegerField()
    assigned_person = models.CharField(max_length=255, blank=True, null=True)
    created_at = models.DateTimeField()
    employee_comment = models.CharField(max_length=255, blank=True, null=True)
    end_date = models.DateField(blank=True, null=True)
    manager_comment = models.CharField(max_length=255, blank=True, null=True)
    modified_at = models.DateTimeField(blank=True, null=True)
    start_date = models.DateField(blank=True, null=True)
    status = models.CharField(max_length=9, blank=True, null=True)
    person = models.ForeignKey(Person, models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'vacation'
