from django.db import models

# Create your models here.

class Crawling(models.Model):
    infox = models.IntegerField(primary_key=True)
    title = models.TextField(blank=True, null=True)
    frame = models.TextField(blank=True, null=True)
    count = models.BigIntegerField(blank=True, null=True)
    intro = models.TextField(blank=True, null=True)
    words = models.TextField(blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'crawling'
