import React, { useState } from "react";
const { Kafka } = require("kafkajs");

export const Logs = () => {
  const [messages, setMessages] = useState([]);

  const addMessage = (message) => {
    setMessages([...messages, message]);
  };

  init();

  async function init() {
    const kafka = new Kafka({
      clientId: "frontend",
      brokers: ["localhost:29092"],
    });
    const consumer = kafka.consumer({ groupId: "frontend" });
    await consumer.connect();
    // It's possible to start from the beginning of the topic
    await consumer.subscribe({ topics: ["LOG"], fromBeginning: true });
    // await consumer.connect()
    // await consumer.subscribe({ topics: [/topic-(eu|us)-.*/i] })
    await consumer.run({
      eachMessage: async ({ topic, partition, message, heartbeat, pause }) => {
        console.log({
          key: message.key.toString(),
          value: message.value.toString(),
          headers: message.headers,
        });
        addMessage(message.value.toString);
      },
    });
  }

  return (
    <div className="logs">
      <h2>Logs</h2>
      <ul>
        {messages.map((message, index) => (
          <li key={index}>{message}</li>
        ))}
        {<li>place holder log !</li>}
      </ul>
      {/* Add a button or other mechanism to call addMessage */}
    </div>
  );
};
