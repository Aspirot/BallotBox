CREATE TABLE boey_voteservice.Votes(
                                       id int not null primary key auto_increment,
                                       dateVoteMade date not null,
                                       rank int not null,
                                       pollId int not null,
                                       pollSubjectId int not null,
                                       electorId int not null,
                                       FOREIGN KEY (pollId) REFERENCES boey_electionservice.Ballots(id),
                                       FOREIGN KEY (pollSubjectId) REFERENCES boey_electionservice.Candidates(id),
                                       FOREIGN KEY (electorId) REFERENCES boey_electorservice.Electors(id)
);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',1,1,3,1);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',2,1,4,1);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',3,1,1,1);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',1,1,3,2);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',3,1,4,2);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',2,1,1,2);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',2,1,3,6);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',1,1,4,6);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',3,1,1,6);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',1,1,3,7);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',2,1,4,7);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',3,1,1,7);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',1,1,3,8);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',3,1,4,8);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('1985-06-17',2,1,1,8);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',1,2,2,1);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',4,2,3,1);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',3,2,4,1);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',2,2,5,1);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',3,2,2,3);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',4,2,3,3);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',2,2,4,3);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',1,2,5,3);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',2,2,2,4);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',4,2,3,4);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',3,2,4,4);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',1,2,5,4);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',1,2,2,5);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',3,2,3,5);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',4,2,4,5);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',2,2,5,5);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',4,2,2,8);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',3,2,3,8);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',1,2,4,8);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',2,2,5,8);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',2,2,2,9);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',4,2,3,9);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',3,2,4,9);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',1,2,5,9);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',4,2,2,11);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',3,2,3,11);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',1,2,4,11);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2000-09-11',2,2,5,11);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',2,3,1,2);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',4,3,2,2);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',1,3,3,2);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',3,3,4,2);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',5,3,5,2);


insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',3,3,1,3);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',5,3,2,3);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',1,3,3,3);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',2,3,4,3);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',4,3,5,3);


insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',1,3,1,4);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',2,3,2,4);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',3,3,3,4);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',4,3,4,4);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',5,3,5,4);


insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',5,3,1,5);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',4,3,2,5);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',3,3,3,5);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',2,3,4,5);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',1,3,5,5);


insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',2,3,1,6);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',4,3,2,6);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',1,3,3,6);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',3,3,4,6);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',5,3,5,6);


insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',4,3,1,7);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',2,3,2,7);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',5,3,3,7);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',3,3,4,7);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',1,3,5,7);


insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',2,3,1,8);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',1,3,2,8);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',3,3,3,8);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',4,3,4,8);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',5,3,5,8);


insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',2,3,1,9);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',5,3,2,9);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',3,3,3,9);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',1,3,4,9);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',4,3,5,9);


insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',1,3,1,10);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',2,3,2,10);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',4,3,3,10);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',3,3,4,10);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',5,3,5,10);


insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',5,3,1,11);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',2,3,2,11);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',1,3,3,11);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',4,3,4,11);

insert into boey_voteservice.Votes(dateVoteMade, `rank`, pollId, pollSubjectId, electorId)
values ('2020-01-03',3,3,5,11);