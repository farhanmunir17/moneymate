/*function showCard(id) {
  document.querySelectorAll(".card").forEach((card) => {
    card.classList.remove("active");
  });
  document.getElementById(id).classList.add("active");
}

const usedEmails = ["admin@moneymate.com"];

document.addEventListener("DOMContentLoaded", () => {
  const signupForm = document.getElementById("formSignup");
  const loginForm = document.getElementById("formLogin");

  if (signupForm) {
    signupForm.addEventListener("submit", function (e) {
      e.preventDefault();

      const name = document.getElementById("signupName").value.trim();
      const email = document.getElementById("signupEmail").value.trim().toLowerCase();
      const password = document.getElementById("signupPassword").value;
      const confirm = document.getElementById("signupConfirm").value;
      const alertBox = document.getElementById("signupAlert");

      alertBox.style.display = "none";

      if (!name || !email || !password || !confirm) {
        return showAlert(alertBox, "Semua field wajib diisi.");
      }
      if (!/^[^@]+@[^@]+\.[^@]+$/.test(email)) {
        return showAlert(alertBox, "Format email tidak valid.");
      }
      if (password.length < 6) {
        return showAlert(alertBox, "Kata sandi minimal 6 karakter.");
      }
      if (password !== confirm) {
        return showAlert(alertBox, "Konfirmasi sandi tidak cocok.");
      }
      if (usedEmails.includes(email)) {
        return showAlert(alertBox, "Email sudah terdaftar.");
      }

      usedEmails.push(email);
      showAlert(alertBox, "Pendaftaran berhasil! Silakan masuk.", "success");
      setTimeout(() => showCard("loginForm"), 1200);
    });
  }

  if (loginForm) {
    loginForm.addEventListener("submit", function (e) {
      e.preventDefault();

      const email = document.getElementById("loginEmail").value.trim().toLowerCase();
      const password = document.getElementById("loginPassword").value;
      const alertBox = document.getElementById("loginAlert");

      alertBox.style.display = "none";

      if (!email || !password) {
        return showAlert(alertBox, "Email dan kata sandi wajib diisi.");
      }
      if (!usedEmails.includes(email)) {
        return showAlert(alertBox, "Akun tidak ditemukan.");
      }

      showAlert(alertBox, "Login berhasil! Mengalihkan ke halaman transaksi...", "success");
      setTimeout(() => {
        window.location.href = "/transaksi"; // Tambahkan /
      }, 1000);
    });
  }
});

function showAlert(el, msg, type = "error") {
  el.className = `alert ${type}`;
  el.textContent = msg;
  el.style.display = "block";
}*/
