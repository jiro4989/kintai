import React, { useState } from 'react';
import './App.css';

function Signup() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [result, setResult] = useState("");

  const onClick = () => {
    const obj = {email: email, password: password}
    const body = Object.keys(obj).map((key)=>key+"="+encodeURIComponent(obj[key])).join("&");

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
      <table>
        <tr>
          <th>
            email:
          </th>
          <td>
            <input type="text" value={email} onChange={(e) => setEmail(e.targetValue)} />
          </td>
        </tr>
        <tr>
          <th>
            password:
          </th>
          <td>
            <input type="password" value={password} onChange={(e) => setPassword(e.targetValue)} />
          </td>
        </tr>
      </table>
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
