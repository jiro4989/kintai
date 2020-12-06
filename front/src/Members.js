import React, { useState } from 'react';
import './App.css';

function Members() {
  const [members, setMembers] = useState([]);

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

  let memberHTML = [];
  for (let m in members) {
    memberHTML.push(
      <tr>
        <td>
          m.id
        </td>
        <td>
          m.email
        </td>
      </tr>
    )
  }

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
