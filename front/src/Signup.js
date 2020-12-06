import React, { useState } from 'react';
import './App.css';

function Signup() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [result, setResult] = useState("");

  const onClick = () => {
    const obj = {email: email, password: password}
    const body = Object.keys(obj).map((key)=>key+"="+encodeURIComponent(obj[key])).join("&");

    fetch("http://localhost/api/v1/signup", {
      method: "POST",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        "mode": "cors",
        "cache": "no-cache",
        "credentials": 'include'
      },
      body: body,
    })
    .then((resp) => resp.json())
    .then((resp) => { setResult(resp.message) })
    .catch((resp) => { console.error(resp) });
  }

  return (
    <div className="Signup">
      Signup
      <table>
        <tbody>
          <tr>
            <th>
              email:
            </th>
            <td>
              <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} />
            </td>
          </tr>
          <tr>
            <th>
              password:
            </th>
            <td>
              <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
            </td>
          </tr>
        </tbody>
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
