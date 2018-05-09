function onProfileLoad(user) {


    clearMessages();
    showContents(['profile-content', 'logout-content']);

    const userEmailSpandEl = document.getElementById('user-name');
    userEmailSpandEl.innerHTML = user.user.email;




    worksTableEl = document.getElementById('works');
    addWorks(user.works);
}

function addWork(work){


       const aEl = document.createElement('a');
       aEl.textContent = work.title;
       aEl.href = 'javascript:void(0);';



       const nameTdEl = document.createElement('td');
       nameTdEl.appendChild(aEl);

       const writtenTdEl = document.createElement('td');
       writtenTdEl.textContent = work.written;

       const trEl = document.createElement('tr');

       trEl.appendChild(nameTdEl);
       trEl.appendChild(writtenTdEl);
       worksTableEl.appendChild(trEl);
   }

function addWorks(works) {
    removeAllChildren(worksTableEl);

    for (let i = 0; i < works.length; i++) {
        const work = works[i];
        addWork(work);
    }
}

