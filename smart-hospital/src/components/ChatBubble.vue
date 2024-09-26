<template>
  <div class="chat-bubble" @click="openChat">
    <img src="../assets/chatbox.png" title="chat icons" class="chat-button" />
    <div class="pulse"></div>
  </div>
</template>

<script>
export default {
  methods: {
    openChat() {
      this.$emit('open-chat'); // Emit event to open the chat window
    }
  }
};
</script>

<style scoped>
.chat-bubble {
  margin-left: 20px;
  bottom: 30px;
  right: 30px;
  background: linear-gradient(135deg, #bcd8f6, #ffd9a5);
  width: 30px;
  height: 30px;
  border-radius: 20%;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0 6px 15px rgba(0, 0, 0, 0.3);
  cursor: pointer;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  overflow: hidden;
}

.chat-bubble:hover {
  transform: scale(1.1);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.4);
}

.chat-bubble img {
  width: 40px;
  height: 40px;
  z-index: 1;
}

.pulse {
  position: absolute;
  width: 30px;
  height: 30px;
  border-radius: 20%;
  background: rgba(0, 123, 255, 0.4);
  animation: pulse 1.5s infinite ease-in-out;
  z-index: 0;
}

@keyframes pulse {
  0% {
    transform: scale(0.8);
    opacity: 1;
  }
  100% {
    transform: scale(1.5);
    opacity: 0;
  }
}

.chat-button {
  display: inline-block;
  padding: 10px 20px; /* Adjust padding for button size */
  background-color: #a9f0d5; /* Button background color */
  color: white; /* Text color */
  text-align: center;
  text-decoration: none; /* Remove underline */
  border-radius: 5px; /* Rounded corners */
  transition: background-color 0.3s; /* Smooth background color transition */
}

.chat-button:hover {
  background-color: #64696f55; /* Darker shade on hover */
}

</style>
