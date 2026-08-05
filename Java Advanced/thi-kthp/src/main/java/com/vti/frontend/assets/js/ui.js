// Toast + confirm modal dùng chung cho mọi trang
(function(){
  let wrap = document.querySelector(".toast-wrap");
  if(!wrap){
    wrap = document.createElement("div");
    wrap.className = "toast-wrap";
    document.body.appendChild(wrap);
  }

  const icons = {
    success:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M20 6 9 17l-5-5"/></svg>',
    error:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="9"/><path d="M12 8v5M12 16h.01"/></svg>',
    info:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="9"/><path d="M12 8h.01M11 12h1v5h1"/></svg>'
  };

  window.toast = function(message, type = "info"){
    const el = document.createElement("div");
    el.className = "toast " + type;
    el.innerHTML = (icons[type]||icons.info) + "<div>" + escapeHtml(message) + "</div>";
    wrap.appendChild(el);
    setTimeout(()=>{ el.style.opacity="0"; el.style.transition="opacity .2s"; setTimeout(()=>el.remove(),200); }, 3400);
  };

  window.confirmDialog = function({ title="Xác nhận", message="Bạn có chắc chắn?", okText="Đồng ý", danger=true }){
    return new Promise((resolve)=>{
      const overlay = document.createElement("div");
      overlay.className = "modal-overlay show";
      overlay.innerHTML = `
        <div class="modal" style="max-width:400px">
          <div class="modal-header"><h2>${escapeHtml(title)}</h2>
            <button class="modal-close" data-act="cancel">✕</button></div>
          <div class="modal-body"><p style="font-size:13.5px;color:var(--ink-soft)">${escapeHtml(message)}</p></div>
          <div class="modal-footer">
            <button class="btn btn-outline" data-act="cancel">Hủy</button>
            <button class="btn ${danger?'btn-danger':'btn-primary'}" data-act="ok">${escapeHtml(okText)}</button>
          </div>
        </div>`;
      document.body.appendChild(overlay);
      overlay.addEventListener("click", (e)=>{
        const act = e.target.closest("[data-act]")?.dataset.act;
        if(e.target === overlay || act === "cancel"){ overlay.remove(); resolve(false); }
        else if(act === "ok"){ overlay.remove(); resolve(true); }
      });
    });
  };

  window.openModal = (id)=> document.getElementById(id)?.classList.add("show");
  window.closeModal = (id)=> document.getElementById(id)?.classList.remove("show");
})();