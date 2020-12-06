import React, { useState } from 'react';
import './App.css';

function Signup() {
  const [result, setResult] = useState("");

  const obj = {email: "unko@gmail.com", password: "unko"}
  const body = Object.keys(obj).map((key)=>key+"="+encodeURIComponent(obj[key])).join("&");

  const onClick = () => {
    fetch("http://localhost:5000/api/v1/signup", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        "mode": "cors",
        "cache": "no-cache",
      },
      body: body,
    }).then((resp) => resp.json())
    .then((resp) => { setResult(resp) })
    .catch(console.error);
  }

  return (
    <div className="Signup">
      Signup
      <button type="button" onClick={onClick}>
        Signup
      </button>
      <div>
        Result: {result}
      </div>
    </div>
  );
}

export default Signup;
