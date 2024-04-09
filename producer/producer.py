from kafka import KafkaProducer
from dotenv import load_dotenv
from kafka.errors import KafkaError
import os;

producer = KafkaProducer(bootstrap_servers=[os.environ['BOOTSTRAP_SERVER']])
load_dotenv(verbose=True)

# Asynchronous by default
future = producer.send('LOG', b'testing')

# Block for 'synchronous' sends
try:
    record_metadata = future.get(timeout=10)
except KafkaError:
    # Decide what to do if produce request failed...
    log.exception()
    pass

# Successful result returns assigned partition and offset
print (record_metadata.topic)
print (record_metadata.partition)
print (record_metadata.offset)

# produce keyed messages to enable hashed partitioning
producer.send('LOG', key=b'test', value=b'test')

