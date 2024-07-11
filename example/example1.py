import requests

def sendMessage(message):
    url = f'http://192.168.97.217:8090/sendmessageAsUser/gpt4?message={message}'
    response = requests.get(url)

    if response.status_code == 200:
        return str(response.text)
    else:
        return str("Error: " + str(response.status_code))

print(sendMessage("Hello!"))
