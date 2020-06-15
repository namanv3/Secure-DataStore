import requests
from sys import argv

URL = "http://localhost:4567/users"

PARAMS = {
	"username": argv[2],
	"password": argv[3]
}

if argv[1] == "1":
	response = requests.post(url = URL, params = PARAMS)
	print(response.text)
else:
	response = requests.get(url = URL, params = PARAMS)
	data = response.json()
	print(data)