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
	if "username" not in data:
	    exit(1)
	while True:
		code = input("1 for adding file, 2 for getting file: ")
		if code == "1":
			filename = input("Enter filename: ")
			data = input("Enter data: ")
			newParams = {
				"filename": filename,
				"data": data
			}
			response = requests.post(url = (URL + "/" + argv[2]), params = newParams)
			print(response.text)
		elif code == "2":
			filename = input("Enter filename: ")
			newParams = {
				"filename": filename
			}
			response = requests.get(url = (URL + "/" + argv[2]), params = newParams)
			data = response.json()
			print(data)