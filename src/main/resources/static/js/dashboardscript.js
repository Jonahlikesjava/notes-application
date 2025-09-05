let notes = [];

document.addEventListener("DOMContentLoaded", () => {
  // Add Note button
  const btn = document.getElementById("addBtn");
  if (btn) btn.addEventListener("click", displayNote);

  // Notes button
  const notesBtn = document.getElementById("notesBtn");
  if (notesBtn) {
    notesBtn.addEventListener("click", (e) => {
      e.preventDefault(); // stop jumping
      showNotes();
    });
  }
});

function displayNote() {
  const title = document.getElementById("noteTitle").value;
  const content = document.getElementById("noteContent").value;
  notes.push({ title, content });

  // clear inputs
  document.getElementById("noteTitle").value = "";
  document.getElementById("noteContent").value = "";
}

function showNotes() {
  const main = document.getElementById("main");
  main.innerHTML = notes.length
    ? notes.map(n => `<h3>${n.title}</h3><p>${n.content}</p>`).join("")
    : "<p>No notes yet.</p>";
}
