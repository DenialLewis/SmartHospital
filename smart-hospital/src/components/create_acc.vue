<template>
  <div class="create-account-container">
    <!-- Close Icon -->
    <button aria-label="Close account form" class="close-btn" @click="goBack">
      <i class="fas fa-times">X</i>
    </button>

    <!-- Image Section -->
    <div class="image-section">
      <img :src="require('@/assets/Hpic.png')" alt="Welcome Image" class="welcome-image" />
    </div>

    <!-- Form Section -->
    <div class="form-section">
      <h1>{{ translations[currentLang].createAccountTitle }}</h1>
      <form @submit.prevent="submitForm">
        <div class="input-group">
          <label for="name">{{ translations[currentLang].name }}</label>
          <input type="text" id="name" v-model="name" required>
        </div>

        <div class="input-group">
          <label for="email">{{ translations[currentLang].email }}</label>
          <input type="email" id="email" v-model="email" required>
        </div>

        <div class="input-group">
          <label for="password">{{ translations[currentLang].password }}</label>
          <input type="password" id="password" v-model="password" required>
        </div>

        <div class="input-group">
          <label for="confirm-password">{{ translations[currentLang].confirmPassword }}</label>
          <input type="password" id="confirm-password" v-model="confirmPassword" required>
        </div>

        <button type="submit" class="primary-btn">{{ translations[currentLang].createAccount }}</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  props: ['currentLang'],
  data() {
    return {
      name: '',
      email: '',
      password: '',
      confirmPassword: '',
      translations: {
        EN: {
          createAccountTitle: "Create Account",
          name: "Name",
          email: "Email",
          password: "Password",
          confirmPassword: "Confirm Password",
          createAccount: "Create Account"
        },
        ไทย: {
          createAccountTitle: "สร้างบัญชี",
          name: "ชื่อ",
          email: "อีเมล",
          password: "รหัสผ่าน",
          confirmPassword: "ยืนยันรหัสผ่าน",
          createAccount: "สร้างบัญชี"
        },
        မြန်မာ: {
          createAccountTitle: "အကောင့်ဖွင့်ပါ",
          name: "နာမည်",
          email: "အီးမေးလ်",
          password: "စကားဝှက်",
          confirmPassword: "စကားဝှက်အတည်ပြုပါ",
          createAccount: "အကောင့်ဖွင့်ပါ"
        }
      }
    };
  },
  methods: {
    goBack() {
      this.$emit('close');
    },
    submitForm() {
      if (this.password !== this.confirmPassword) {
        alert(this.translations[this.currentLang].passwordMismatch);
        return;
      }
      alert('Account created successfully!');
      this.$emit('close');
    }
  }
};
</script>

<style scoped>
.create-account-container {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #fff;
  padding: 30px;
  border-radius: 12px;
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
  width: 450px;
  max-width: 90%;
}

body {
  overflow: hidden;
}

.close-btn {
  position: absolute;
  top: 15px;
  right: 15px;
  background: none;
  border: none;
  font-size: 18px;
  cursor: pointer;
  color: #999;
}

.close-btn:hover {
  color: #333;
}

.image-section {
  text-align: center;
  margin-bottom: 20px;
}

.welcome-image {
  width: 100%;
  max-width: 200px;
  border-radius: 50%;
  margin-bottom: 20px;
}

.form-section h1 {
  font-size: 24px;
  font-weight: 600;
  margin-bottom: 20px;
  color: #333;
  text-align: center;
}

.input-group {
  margin-bottom: 15px;
}

.input-group label {
  display: block;
  font-size: 14px;
  margin-bottom: 5px;
  color: #666;
}

.input-group input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
  transition: border-color 0.3s ease;
}

.input-group input:focus {
  border-color: #007BFF;
}

.primary-btn {
  width: 100%;
  background-color: #007BFF;
  color: white;
  padding: 10px;
  border: none;
  border-radius: 6px;
  font-size: 16px;
  cursor: pointer;
  margin-top: 20px;
  transition: background-color 0.3s ease;
}

.primary-btn:hover {
  background-color: #0056b3;
}
</style>
