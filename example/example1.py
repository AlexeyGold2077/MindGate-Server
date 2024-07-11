import requests

message = 'hi'
userId = '123'
localServerIP = 'localhost:8090'
# gpt4 = 'gpt4'
gpt4o = 'gpt-4o'
gpt4turbo = 'gpt-4-turbo'


def sendMessage(message, localServerIP, userId, model):
    url = f'http://{localServerIP}/sendmessageAsUser/{model}?userId={userId}&message={message}'
    print(url)
    response = requests.get(url)

    if response.status_code == 200:
        return str(response.text)
    else:
        return str("Error: " + str(response.status_code))


print(sendMessage(message, localServerIP, userId, gpt4o))
