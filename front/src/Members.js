import React, { useState, useEffect } from 'react';
import './App.css';

function Members() {
  const [members, setMembers] = useState([]);

  useEffect(() => {
    fetch("http://localhost/api/v1/members", {
      method: "GET",
      headers: {
        "Content-Type": "application/x-www-form-urlencoded",
        "mode": "cors",
        "cache": "no-cache",
        "credentials": 'include'
      },
    })
    .then((resp) => resp.json())
    .then((resp) => { setMembers(resp.members) })
    .catch((resp) => { console.error(resp) });
  }, [])

  const memberHTML = members.map((m) => {
    return <tr key={m.id}>
      <td>
        {m.id}
      </td>
      <td>
        {m.email}
      </td>
    </tr>
  })

  return (
    <div className="Members">
      Members
      <table>
        <thead>
          <tr>
            <td>
              id
            </td>
            <td>
              email
            </td>
          </tr>
        </thead>

        <tbody>
          {memberHTML}
        </tbody>
      </table>
    </div>
  );
}

export default Members;
