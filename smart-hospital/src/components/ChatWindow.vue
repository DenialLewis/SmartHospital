<template>
  <div v-if="isOpen" class="chat-window">
    <div class="chat-header">
      <span class="title">Medical Assistant</span>
      <button @click="closeChat">X</button>
    </div>

    <div class="chat-body">
      <div class="message bot-message" v-for="message in botMessages" :key="message.id">
        {{ message.text }}
      </div>
      <div class="message user-message" v-for="message in userMessages" :key="message.id">
        {{ message.text }}
      </div>
    </div>

    <div class="chat-footer">
      <input v-model="userInput" type="text" placeholder="Type your symptoms..." />
      <button @click="sendMessage">Send</button>
    </div>
  </div>
</template>

<script>
export default {
  props: ['isOpen'],
  data() {
    return {
      userInput: '',
      botMessages: [{ id: 1, text: 'Hi! How can I assist you?' }],
      userMessages: []
    };
  },
  methods: {
    closeChat() {
      this.$emit('close-chat'); // Emit event to close the chat
    },
    async sendMessage() {
      if (this.userInput.trim() !== '') {
        // Add user message
        const userMessage = { id: Date.now(), text: this.userInput };
        this.userMessages.push(userMessage);
        
        // Clear input
        const input = this.userInput;
        this.userInput = '';

        // Send the message to the AI service
        const response = await this.getAIResponse(input);

        // Add AI response to chat
        this.botMessages.push({ id: Date.now(), text: response });
      }
    },
    async getAIResponse(userInput) {
    try {
        const response = await fetch('/api/chat', {  // Ensure the path is correct
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ message: userInput }),
        });

        // Check if the response is OK (status code 200)
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }

        const data = await response.json();
        return data.response; // Adjusted to extract the response from the ChatResponse object
    } catch (error) {
        console.error('Error fetching AI response:', error);
        return 'Sorry, something went wrong.';
    }
}



  }
};
</script>

<style scoped>
.chat-window {
 position: fixed;
 bottom: 20px; /* Adjust distance from the bottom */
 right: 20px;  /* Adjust distance from the right */
 width: 400px;
 height: 600px;
 background-color: #fff;
 box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
 border-radius: 10px;
 display: flex;
 flex-direction: column;
 z-index: 1000; /* Ensure it's above other elements */
}

 
 .chat-header {
   background-color: #1e90ff;
   color: white;
   padding: 10px;
   display: flex;
   justify-content: space-between;
   align-items: center;
   border-radius: 10px 10px 0 0;
 }
 
 .chat-body {
   flex: 1;
   padding: 15px;
   overflow-y: auto;
   background-color: #f5f5f5;
 }
 
 .message {
   margin-bottom: 10px;
 }
 
 .bot-message {
   text-align: left;
   background-color: #eee;
   padding: 10px;
   border-radius: 10px;
 }
 
 .user-message {
   text-align: right;
   background-color: #1e90ff;
   color: white;
   padding: 10px;
   border-radius: 10px;
 }
 
 .chat-footer {
   display: flex;
   padding: 10px;
   background-color: white;
   border-top: 1px solid #ddd;
 }
 
 .chat-footer input {
   flex: 1;
   padding: 10px;
   border: none;
   background-color: #f0f0f0;
   border-radius: 5px;
   margin-right: 10px;
 }
 
 .chat-footer button {
   background-color: #1e90ff;
   color: white;
   border: none;
   padding: 10px;
   border-radius: 5px;
   cursor: pointer;
 }
 </style>
