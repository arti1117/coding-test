from django.shortcuts import render, redirect
from django.core.paginator import Paginator
from .models import Crawling
from django.db.models import Q


from django.views import View, generic

# Create your views here.

class board(generic.TemplateView):

    # def get(self, request, *args, **kwargs):
    #     crawling = Crawling.objects.all()
    #     return render(request, 'board/board.html', {"crawling": crawling})

    def get(self, request, *args, **kwargs):
        crawling = Crawling.objects.all()
        paginator = Paginator(crawling, 10)
        page = request.GET.get('page')
        craws = paginator.get_page(page)
        return render(request, 'board/board.html', {"craws": craws})


def index(request):
    if request.GET.get('q'):
        variable_column = request.GET.get('fd_name')
        search_type = 'contains'
        filters = variable_column + '__' + search_type
        posts = Crawling.objects.filter(**{filters: request.GET.get('q')}).order_by('infox')
        paginator = Paginator(posts, 10)
        page = request.GET.get('page')
        craws = paginator.get_page(page)
    return render(request, 'board/result.html', {'craws': craws})