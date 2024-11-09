from rest_framework import serializers

from . import models

class TimesheetSerializer(serializers.ModelSerializer):
    
    class Meta:
        model = models.Timesheet
        fields = (
            'id', 
            'assigned_person', 
            'assigned_project', 
            'category', 
            'difference', 
            'end_time', 
            'start_time',
            'summary', 
            'task', 
            'person',
            'project'
        )

    def create(self, validated_data):
        timesheet = models.Timesheet(
            id = validated_data['id'],
            assigned_person = validated_data['assigned_person'],
            assigned_project = validated_data['assigned_project'],
            category = validated_data['category'],
            difference = validated_data['difference'],
            end_time = validated_data['end_time'],
            start_time = validated_data['start_time'],
            summary = validated_data['summary'],
            task = validated_data['task'],
            person_id = validated_data['person'],
            project_id = validated_data['project']
        )

        return timesheet
