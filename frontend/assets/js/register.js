document.getElementById("formAuthentication").addEventListener("submit", async function (e) {
  e.preventDefault();

  const payload = {
    email: document.getElementById("email").value.trim(),
    password: document.getElementById("password").value,
    firstName: document.getElementById("firstName").value.trim(),
    lastName: document.getElementById("lastName").value.trim(),
    phoneNumber: document.getElementById("phoneNumber").value.trim(),
    referredBy: document.getElementById("referredBy").value.trim()
  };

  const messageEl = document.getElementById("message");

  function showBootstrapModal(message, title = "Notification") {
  const modalLabel = document.getElementById("responseModalLabel");
  const modalMessage = document.getElementById("modalMessage");

  modalLabel.textContent = title;
  modalMessage.innerHTML = message;

  const modal = new bootstrap.Modal(document.getElementById("responseModal"));
  modal.show();
}


  async function getToken() {
    const response = await fetch("http://localhost:8081/api/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: "george@mailinator.com",
        password: "password"
      })
    });

    if (!response.ok) {
      const error = await response.text();
      throw new Error(`Failed to get token: ${response.status} - ${error}`);
    }

    const data = await response.json();
    localStorage.setItem("jwtToken", data.token);
    return data.token;
    
  }

  async function registerUser(data) {
    try {
      const token = await getToken();

      const response = await fetch("http://localhost:8081/api/auth/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(data)
      });

      const result = await response.json();

      if (response.ok) {
         showBootstrapModal(`✅ Verification email sent to: ${data.email}`, "Registration Successful");
      } else {
        showBootstrapModal(`❌ ${result.message || "Registration failed."}`, "Error");
      }


    } catch (error) {
      console.error(error);
      showBootstrapModal(`⚠️ ${error.message || "Server error."}`, "Request Failed");

    }
  }

  registerUser(payload);
});
