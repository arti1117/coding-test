from django.conf.urls import url
from . import views
from django.urls import path

from django.conf import settings
from django.conf.urls.static import static

app_name = 'board'

urlpatterns = [
    url(r'^$', views.board.as_view(), name='board'),
    url(r'^$', views.index, name='index'),
]

urlpatterns += static(settings.CRAWL_URL, document_root=settings.CRAWL_ROOT)