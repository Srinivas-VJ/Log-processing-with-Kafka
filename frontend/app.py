from flask import Flask
from dotenv import load_dotenv
from kafka.consumer import KafkaConsumer
import logging
import os
import json

app = Flask(__name__)

@app.route("/")
def landing_page():
    return "<h1>Sastha Kibana</h1>"

if __name__ == '__main__':
    load_dotenv(verbose=True)
    logging.basicConfig(level=logging.INFO)
    logger = logging.getLogger(__name__)

    logger.info("Starting consumer ", os.environ["BOOTSTRAP_SERVER"])
    consumer = KafkaConsumer( 
    bootstrap_servers=[os.environ["BOOTSTRAP_SERVER"]],
    auto_offset_reset="earliest",
    enable_auto_commit=True);
    consumer.subscribe([os.environ["TOPIC"]])

    for message in consumer:
        message = f"""
        Message received: {message.value}
        Message key: {message.key}
        Message partition: {message.partition}
        Message offset: {message.offset}
        """
        logger.info(message)

    app.run(debug = True);

