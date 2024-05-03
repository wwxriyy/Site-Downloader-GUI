import sys
from pywebcopy import save_webpage, save_website
import validators

def warning(text):
    print("\033[1m\033[31m{}\033[0m".format(text))

def webpage(url, folder, name):
    save_webpage(
        url=url,
        project_folder=folder,
        project_name=name,
        bypass_robots=True,
        debug=True,
        open_in_browser=True,
        delay=None,
        threaded=False,
    )

def website(url, folder, name):
    save_website(
        url=url,
        project_folder=folder,
        project_name=name,
        bypass_robots=True,
        debug=True,
        open_in_browser=True,
        delay=None,
        threaded=False,
    )

args = sys.argv[1:]
if len(args) != 4:
    print("Неверное количество аргументов!")
    sys.exit(1)

url, folder, name, downloadType = args

if downloadType == "1":
    webpage(url, folder, name)
elif downloadType == "2":
    website(url, folder, name)
else:
    warning("Выберите корректный тип скачивания!")