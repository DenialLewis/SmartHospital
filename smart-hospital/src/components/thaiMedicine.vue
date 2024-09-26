<template>
  <div class="doctor-schedule-page">
    <!-- Header Section -->
    <header class="header">
      <div class="logo">
        <img alt="Mfu Logo" src="../assets/Hospital.png" @click="goToHomePage" style="cursor: pointer;" />
      </div>
      <div class="search-lang">
        <input type="text" class="search-bar" :placeholder="translations[currentLang].searchPlaceholder" />
        <div class="lang-switch">
          <div class="lang-dropdown" @click="toggleLanguageDropdown">
            <span>{{ currentLang }}</span>
            <img
              :src="showLanguageDropdown ? require('@/assets/up_arr.png') : require('@/assets/down_arr.png')"
              alt="Dropdown Arrow Icon"
              class="down-arrow"
            />
          </div>
          <div v-if="showLanguageDropdown" class="dropdown-menu">
            <button v-if="currentLang !== 'EN'" @click="setLanguage('EN')" class="dropdown-item">
              <img src="../assets/uk.png" alt="English Icon" class="dropdown-icon" /> EN
            </button>
            <button v-if="currentLang !== 'ไทย'" @click="setLanguage('ไทย')" class="dropdown-item">
              <img src="../assets/th.png" alt="Thai Icon" class="dropdown-icon" /> ไทย
            </button>
            <button v-if="currentLang !== 'မြန်မာ'" @click="setLanguage('မြန်မာ')" class="dropdown-item">
              <img src="../assets/mm.png" alt="Myanmar Icon" class="dropdown-icon" /> မြန်မာ
            </button>
          </div>

          <!-- Profile Dropdown -->
          <div class="profile-container" @click="toggleProfileDropdown">
            <img src="../assets/profile.png" alt="Profile Icon" />
            <img
              :src="showProfileDropdown ? require('@/assets/up_arr.png') : require('@/assets/down_arr.png')"
              alt="Dropdown Arrow Icon"
              class="down-arrow"
            />
          </div>
          <div v-if="showProfileDropdown" class="dropdown-menu">
            <button class="dropdown-item" @click="openLoginForm">
              <img src="../assets/profile.png" alt="Login Logo" class="dropdown-icon" />
              {{ translations[currentLang].login }}
            </button>
            <button class="dropdown-item" @click="openCreateAccountForm">
              <img src="../assets/create-acc.png" alt="Create Account Icon" class="dropdown-icon" />
              {{ translations[currentLang].createAccount }}
            </button>
          </div>
        </div>
      </div>
    </header>

    <!-- Navigation Section -->
    <nav class="nav-tabs">
      <button class="tab" :class="{ 'active': showDoctorDropdown }" @click="toggleDoctorDropdown">
        {{ translations[currentLang].doctorSchedule }}
      </button>
      <button class="tab">{{ translations[currentLang].appointments }}</button>
      <button class="tab">{{ translations[currentLang].services }}</button>

      <!-- Doctor Dropdown -->
      <div v-if="showDoctorDropdown" class="doctor-dropdown">
        <router-link to="/thai-medicine">
          <button class="dropdown-item" @click="selectDoctorDepartment(translations[currentLang].departments.thaiMedicine)">
            {{ translations[currentLang].departments.thaiMedicine }}
          </button>
        </router-link>
        <button class="dropdown-item" @click="selectDoctorDepartment(translations[currentLang].departments.chineseMedicine)">
    {{ translations[currentLang].departments.chineseMedicine }}
  </button>
  <button class="dropdown-item" @click="selectDoctorDepartment(translations[currentLang].departments.physicalTherapy)">
    {{ translations[currentLang].departments.physicalTherapy }}
  </button>
  <button class="dropdown-item" @click="selectDoctorDepartment(translations[currentLang].departments.outpatientClinic)">
    {{ translations[currentLang].departments.outpatientClinic }}
  </button>
      </div>
    </nav>

    <!-- Page Title -->
    <header class="page-header">
      <h1>{{ translations[currentLang].doctorSchedule }}</h1>
      
    </header>

    <!-- Schedule Section -->
    <section class="schedule-container">
      <div v-for="(department, index) in departments" :key="index" class="department-section">
        <h2>{{ department.name }}</h2>
        <table class="schedule-table">
          <thead>
            <tr>
              <th>{{ translations[currentLang].doctorName }}</th>
              <th>{{ translations[currentLang].specialization }}</th>
              <th>{{ translations[currentLang].availableTime }}</th>
              <th>{{ translations[currentLang].appointment }}</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(doctor, i) in department.doctors" :key="i">
              <td>{{ doctor.name }}</td>
              <td>{{ doctor.specialization }}</td>
              <td>{{ doctor.availableTime }}</td>
              <td><button @click="bookAppointment(doctor)">{{ translations[currentLang].bookNow }}</button></td>
            </tr>
          </tbody>
        </table>
      </div>
    </section>

    <LogIn v-if="showLoginForm" @close="showLoginForm = false" :currentLang="currentLang" />
    <CreateAcc v-if="showCreateAccountForm" @close="showCreateAccountForm = false" :currentLang="currentLang" />
  </div>
</template>

<script>
import LogIn from '../components/log_in.vue';
import CreateAcc from '../components/create_acc.vue';

export default {
  name: 'ThaiMedicine',
  data() {
    return {
      showLanguageDropdown: false,
      showProfileDropdown: false,
      showDoctorDropdown: false,
      showLoginForm: false,
      showCreateAccountForm: false,
      currentLang: 'EN',
      translations: {
        EN: {
          doctorSchedule: "Doctor's Schedule",
          searchPlaceholder: "Search Doctor or Department...",
          doctorName: "Doctor Name",
          specialization: "Specialization",
          availableTime: "Available Time",
          appointment: "Appointment",
          bookNow: "Book Now",
          appointments: "Appointments",
          services: "Services",
          login: "Login",
          createAccount: "Create Account",
          departments: {
            thaiMedicine: "Department of Thai Traditional Medicine",
      chineseMedicine: "Department of Chinese Medicine",
      physicalTherapy: "Physical Therapy Department",
      outpatientClinic: "Outpatient Clinic"
          }
        },
        ไทย: {
          doctorSchedule: "ตารางหมอ",
          searchPlaceholder: "ค้นหาหมอหรือแผนก...",
          doctorName: "ชื่อหมอ",
          specialization: "ความชำนาญ",
          availableTime: "เวลาที่พร้อม",
          appointment: "นัดหมาย",
          bookNow: "จองตอนนี้",
          appointments: "นัดหมาย",
          services: "บริการ",
          login: "เข้าสู่ระบบ",
          createAccount: "สร้างบัญชี",
          departments: {
            thaiMedicine: "แผนกการแพทย์แผนไทย",
      chineseMedicine: "แผนกการแพทย์แผนจีน",
      physicalTherapy: "แผนกกายภาพบำบัด",
      outpatientClinic: "แผนกผู้ป่วยนอก"
          }
        },
        မြန်မာ: {
          doctorSchedule: "ဆရာဝန် အချိန်ဇယား",
          searchPlaceholder: "ဆရာဝန် သို့မဟုတ် ဌာနရှာပါ...",
          doctorName: "ဆရာဝန် အမည်",
          specialization: "အထူးပြုဘာသာရပ်",
          availableTime: "ရရှိနိုင်ချိန်",
          appointment: "ချိန်းဆိုချက်",
          bookNow: "အခုပဲ ကြိုတင်မှာယူပါ",
          appointments: "ချိန်းဆိုချက်များ",
          services: "ဝန်ဆောင်မှုများ",
          login: "ဝင်ပါ",
          createAccount: "အကောင့်ဖွင့်ပါ",
          departments: {
            thaiMedicine: "ထိုင်းထူးပြုဆေးရုံ",
            chineseMedicine: "တရုတ် တိုင်းရင်းဆေး၀ါး ဌာန",
      physicalTherapy: "ရုပ်ပိုင်းဆိုင်ရာကုထုံးဌာန",
      outpatientClinic: "ပြင်ပလူနာဌာန"
          }
        }
      },
      departments: [
        {
          name: "Thai Traditional Medicine",
          doctors: [
            { name: "Dr. Somchai", specialization: "Herbal Therapy", availableTime: "Mon-Fri 9:00 AM - 12:00 PM" },
            { name: "Dr. Anan", specialization: "Massage Therapy", availableTime: "Mon-Wed 1:00 PM - 4:00 PM" },
          ],
        },
        {
          name: "Chinese Medicine",
          doctors: [
            { name: "Dr. Li", specialization: "Acupuncture", availableTime: "Tue-Thu 10:00 AM - 2:00 PM" },
            { name: "Dr. Wang", specialization: "Herbal Medicine", availableTime: "Fri 11:00 AM - 3:00 PM" },
          ],
        },
      ],
    };
  },
  components: {
    LogIn,
    CreateAcc
  },
  methods: {
    toggleLanguageDropdown() {
      this.showLanguageDropdown = !this.showLanguageDropdown;
      this.showProfileDropdown = false;
      this.showDoctorDropdown = false;
    },
    toggleProfileDropdown() {
      this.showProfileDropdown = !this.showProfileDropdown;
      this.showLanguageDropdown = false;
      this.showDoctorDropdown = false;
    },
    toggleDoctorDropdown() {
      this.showDoctorDropdown = !this.showDoctorDropdown;
      this.showLanguageDropdown = false;
      this.showProfileDropdown = false;
    },
    setLanguage(lang) {
      this.currentLang = lang;
      this.showLanguageDropdown = false;
    },
    openLoginForm() {
      this.showLoginForm = true;
      this.showProfileDropdown = false;
    },
    openCreateAccountForm() {
      this.showCreateAccountForm = true;
      this.showProfileDropdown = false;
    },
    selectDoctorDepartment(department) {
      if (department === this.translations[this.currentLang].departments.thaiMedicine) {
        this.$router.push({ name: 'thaiMedicine' });
      }
      this.showDoctorDropdown = false;
    },
    goToHomePage() {
      this.$router.push({ name: 'App' }); // Ensure that 'home' route is defined in your router.
    }
  }
};
</script>

<style scoped>
.doctor-schedule-page {
  font-family: Arial, sans-serif;
  padding: 20px;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 20px;
  background-color: #f5f5f5;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.logo img {
  margin-left: 0;
  height: 70px;
}
.search-lang {
  display: flex;
  align-items: center;
}

.search-bar {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-right: 10px;
}

.lang-switch {
  position: relative;
}

.lang-dropdown {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.dropdown-menu {
  position: absolute;
  top: 100%;
  left: 0;
  background: white;
  border: 1px solid #ccc;
  border-radius: 4px;
  z-index: 1000;
  width: 150px;
}

.dropdown-item {
  padding: 10px;
  cursor: pointer;
  text-align: left;
}

.dropdown-item:hover {
  background-color: #f0f0f0;
}

.profile-container {
  cursor: pointer;
  display: flex;
  align-items: center;
}

.profile-container img {
  width: 30px;
  height: 30px;
  margin-left: 10px;
}

.nav-tabs {
  display: flex;
  background-color: #e5e5e5;
  padding: 10px;
}

.tab {
  background: none;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
  transition: background 0.3s;
}

.tab.active {
  background-color: #EBD5A0;
  border-radius: 4px;
}

.schedule-container {
  margin-top: 20px;
}

.department-section {
  margin-bottom: 20px;
}

.schedule-table {
  width: 100%;
  border-collapse: collapse;
}

.schedule-table th,
.schedule-table td {
  border: 1px solid #ccc;
  padding: 10px;
  text-align: left;
}

.schedule-table th {
  background-color: #f2f2f2;
}

button {
  background-color: #4CAF50;
  color: white;
  border: none;
  padding: 8px 16px;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}

.down-arrow {
  width: 10px;
  height: 10px;
  margin-left: 5px;
}

.page-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.search-container {
  flex-grow: 1;
}
.hospital-dashboard {
  display: flex;
  flex-direction: column;
  min-height: 100vh; /* Ensure the body takes at least the full viewport height */
  justify-content: center;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #ffffff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.logo img {
  margin-left: 0;
  height: 70px;
}

.search-lang {
  display: flex;
  align-items: center;
}

.search-bar {
  border: 1px solid #ddd;
  padding: 8px;
  border-radius: 25px;
  width: 250px;
  outline: none;
  box-shadow: inset 0 1px 2px rgba(0, 0, 0, 0.1);
}

.lang-switch {
  margin-left: 15px;
  display: flex;
  align-items: center;
  position: relative;
}

.lang-dropdown, .profile-container {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.lang-switch span {
  font-weight: bold;
  margin-left: 8px;
  color: #003d73; 
}

.lang-switch img, .profile-container img {
  height: 24px;
  width: 24px;
}

/* Dropdown Menu */
.dropdown-menu {
  position: absolute;
  top: 30px;
  right: 0;
  background-color: #ffffff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 6px;
  border-top: 3px solid #EBD5A0; /* Highlighted border color */
  z-index: 1000;
  padding: 0; /* Ensure no extra padding around the menu */
}

.dropdown-item {
  padding: 12px 20px;
  background: none;
  border: none;
  cursor: pointer;
  font-size: 15px;
  text-align: left;
  color: #333;
  display: flex;
  align-items: center;
  width: 100%; /* Ensures the button takes full width */
  transition: background-color 0.3s ease, color 0.3s ease;
}

.dropdown-item:hover {
  background-color: #B5DEE0; /* Hover background color */
  color: #ffffff; /* Hover text color */
}

/* For icon color in dropdown buttons */
.dropdown-icon {
  height: 20px;
  width: 20px;
  margin-right: 10px; /* Space between icon and text */
  transition: filter 0.3s ease; /* Smooth transition for icon */
}

.dropdown-item:hover .dropdown-icon {
  filter: brightness(0) invert(1); /* Changes the icon color to white on hover */
}



/* Navigation Tabs */
.nav-tabs {
  display: flex;
  justify-content: space-around;
  background-color: #ffffff; /* Background color */
  padding: 10px 0;
  border-bottom: 1px solid #e0e0e0;
  position: relative; /* Ensures dropdown is positioned correctly */
}

.tab {
  background: none;
  border: none;
  color: #003d73;
  font-size: 16px;
  padding: 10px 20px;
  cursor: pointer;
  transition: color 0.3s;
}

.tab.active {
  color: #003d73;
  border-top: 3px solid #EBD5A0; /* Highlighted top border */
}

.tab:hover {
  color: #B5DEE0; /* Changed to #B5DEE0 */
}


/* Doctor Dropdown */
.doctor-dropdown {
  position: absolute;
  top: 60px; /* Adjusted to appear below the nav bar */
  left: 0;
  background-color: #ffffff; /* Background color */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 6px;
  border-top: 3px solid #EBD5A0; /* Highlighted border color */
  z-index: 1000;
}

.dropdown-item:hover {
  background-color: #B5DEE0; /* Hover background color */
  color: #ffffff; /* Hover text color */
}

.dropdown-item:last-of-type {
  border-bottom: none;
}
</style>
