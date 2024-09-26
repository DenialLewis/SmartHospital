<template>
  <div class="login-container">
    <!-- Close Icon -->
    <button aria-label="Close login form" class="close-btn" @click="goBack">
  <i class="fas fa-times">x</i>
</button>



    <!-- Image Section -->
    <div class="image-section">
      <img :src="require('@/assets/Hpic.png')" alt="Welcome Image" class="welcome-image" />
    </div>

    <!-- Login Form Section -->
    <div class="form-section">
      <h1>{{ translations[currentLang].welcomeMessage }}</h1>
      <p>{{ translations[currentLang].accessInfo }}</p>

      <!-- Primary Login Options -->
      <div class="primary-login-options">
        <button @click="loginWithEmail" class="primary-btn">
          <i class="fas fa-envelope"></i> {{ translations[currentLang].continueWithEmail }}
        </button>
        <button @click="loginWithPhone" class="primary-btn">
          <i class="fas fa-phone"></i> {{ translations[currentLang].continueWithPhone }}
        </button>
      </div>

      <div class="divider">{{ translations[currentLang].or }}</div>

      <!-- Social Login Options -->
      <div class="social-login">
        <button @click="loginWithGoogle" class="social-btn google-btn">
          <i class="fab fa-google"></i> {{ translations[currentLang].continueWithGoogle }}
        </button>
        <button @click="loginWithApple" class="social-btn apple-btn">
          <i class="fab fa-apple"></i> {{ translations[currentLang].continueWithApple }}
        </button>
        <button @click="loginWithFacebook" class="social-btn facebook-btn">
          <i class="fab fa-facebook-f"></i> {{ translations[currentLang].continueWithFacebook }}
        </button>
        <button @click="loginWithX" class="social-btn x-btn">
          <i class="fab fa-twitter"></i> {{ translations[currentLang].continueWithX }}
        </button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'LogIn',
  props: {
    currentLang: {
      type: String,
      required: true,
      default: 'EN' // Provide a default language in case it's not passed properly
    }
  },
  data() {
    return {
      translations: {
        EN: {
          welcomeMessage: "Welcome to Our Hospital",
          accessInfo: "Access your patient history, lab results, future appointments, and more.",
          continueWithEmail: "Continue with email address",
          continueWithPhone: "Continue with phone number",
          continueWithGoogle: "Continue with Google",
          continueWithApple: "Continue with Apple",
          continueWithFacebook: "Continue with Facebook",
          continueWithX: "Continue with X",
          or: "or"
        },
        ไทย: {
          welcomeMessage: "ยินดีต้อนรับสู่โรงพยาบาลของเรา",
          accessInfo: "เข้าถึงประวัติผู้ป่วย ผลการตรวจ ห้องนัดหมายล่วงหน้า และอื่น ๆ",
          continueWithEmail: "ดำเนินการต่อด้วยที่อยู่อีเมล",
          continueWithPhone: "ดำเนินการต่อด้วยหมายเลขโทรศัพท์",
          continueWithGoogle: "ดำเนินการต่อด้วย Google",
          continueWithApple: "ดำเนินการต่อด้วย Apple",
          continueWithFacebook: "ดำเนินการต่อด้วย Facebook",
          continueWithX: "ดำเนินการต่อด้วย X",
          or: "หรือ"
        },
        မြန်မာ: {
          welcomeMessage: "ကျွန်ုပ်တို့၏ ဆေးရုံမှ ကြိုဆိုပါသည်",
          accessInfo: "သင့်ရဲ့ လူနာမှတ်တမ်း၊ စမ်းသပ်မှုရလဒ်များ၊ လာမယ့် ချိန်းဆိုမှုများ အစရှိသည့်အချက်အလက်များကို ရယူနိုင်ပါသည်။",
          continueWithEmail: "အီးမေးလ်လိပ်စာဖြင့်ဆက်လက်လုပ်ဆောင်ပါ",
          continueWithPhone: "ဖုန်းနံပါတ်ဖြင့် ဆက်လက်လုပ်ဆောင်ပါ",
          continueWithGoogle: "Google ဖြင့် ဆက်လက်လုပ်ဆောင်ပါ",
          continueWithApple: "Apple ဖြင့် ဆက်လက်လုပ်ဆောင်ပါ",
          continueWithFacebook: "Facebook ဖြင့် ဆက်လက်လုပ်ဆောင်ပါ",
          continueWithX: "X ဖြင့် ဆက်လက်လုပ်ဆောင်ပါ",
          or: "သို့မဟုတ်"
        }
      }
    };
  },
  computed: {
    currentTranslation() {
      // Fallback to 'EN' if the currentLang translation is not found
      return this.translations[this.currentLang] || this.translations.EN;
    }
  },
  methods: {
    goBack() {
      this.$emit('close'); // Close the pop-up when clicked
    },
    loginWithEmail() {
      console.log("Logging in with email...");
    },
    loginWithPhone() {
      console.log("Logging in with phone...");
    },
    loginWithGoogle() {
      console.log("Logging in with Google...");
    },
    loginWithApple() {
      console.log("Logging in with Apple...");
    },
    loginWithFacebook() {
      console.log("Logging in with Facebook...");
    },
    loginWithX() {
      console.log("Logging in with X...");
    }
  }
};
</script>
<style scoped>
.login-container {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: rgba(181, 222, 224, 0.9); /* Semi-transparent background */
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.5); /* Adjust shadow for better contrast */
  width: 400px; /* Set width as needed */
}


body {
  overflow: hidden; /* Prevent the background from scrolling when the modal is open */
}

.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 24px;
  color: #333;
  cursor: pointer;
}
.close-btn:hover {
  color: #EBD5A0; /* Hover color for 'X' */
}

.image-section {
  text-align: center;
}

.welcome-image {
  width: 100%;
  max-width: 300px;
  border-radius: 8px;
}

.form-section {
  margin-top: 20px;
}

.primary-login-options {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.primary-btn {
  background-color: #003d73;
  color: #fff;
  border: none;
  padding: 10px;
  border-radius: 5px;
  cursor: pointer;
}

.primary-btn i {
  margin-right: 10px;
}

.divider {
  text-align: center;
  margin: 20px 0;
}

.social-login {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.social-btn {
  background: none;
  border: 1px solid #ddd;
  padding: 10px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
  display: flex;
  align-items: center;
}

.social-btn i {
  margin-right: 10px;
}

.google-btn {
  color: #4285F4;
}

.apple-btn {
  color: #000;
}

.facebook-btn {
  color: #1877F2;
}

.x-btn {
  color: #1DA1F2;
}

</style>
