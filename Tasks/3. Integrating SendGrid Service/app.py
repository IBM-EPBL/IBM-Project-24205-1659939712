import sendgrid
import os
from sendgrid.helpers.mail import Mail, Email, To, Content

sg = sendgrid.SendGridAPIClient(api_key='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')
from_email = Email("vikramrajkumar1828@gmail.com")  # Change to your verified sender
to_email = To("kumarvikram18302@gmail.com")  # Change to your recipient
subject = "Sending with SendGrid is Fun"
content = Content("text/plain", "and easy to do anywhere, even with Python")
mail = Mail(from_email, to_email, subject, content)

# Get a JSON-ready representation of the Mail object
mail_json = mail.get()

# Send an HTTP POST request to /mail/send
response = sg.client.mail.send.post(request_body=mail_json)
print(response.status_code)
print(response.headers)
