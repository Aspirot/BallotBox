CREATE TABLE boey_electionservice.Candidates (
                                                 id int not null primary key auto_increment,
                                                 name varchar(30) not null,
                                                 description varchar(125) not null,
                                                 image varchar(125) not null
);

CREATE TABLE boey_electionservice.Ballots(
                                             id int not null primary key auto_increment,
                                             title varchar(50) not null,
                                             start date not null,
                                             end date,
                                             isPublic boolean not null,
                                             isAnonymous boolean not null,
                                             ownerId int not null,
                                             forumId int not null,
                                             FOREIGN KEY (ownerId) REFERENCES boey_electorservice.Electors(id)
);
CREATE TABLE boey_electionservice.BallotElector(
                                                   id int not null primary key auto_increment,
                                                   pollId int not null,
                                                   electorId int not null,
                                                   FOREIGN KEY (pollId) REFERENCES boey_electionservice.Ballots(id),
                                                   FOREIGN KEY (electorId) REFERENCES boey_electorservice.Electors(id)
);
CREATE TABLE boey_electionservice.BallotCandidate(
                                                     id int not null primary key auto_increment,
                                                     pollId int not null,
                                                     candidateId int not null,
                                                     FOREIGN KEY (pollId) REFERENCES boey_electionservice.Ballots(id),
                                                     FOREIGN KEY (candidateId) REFERENCES boey_electionservice.Candidates(id)
);

insert into boey_electionservice.Candidates(name, description, image)
values('Joe Blow','Blows away all competitors','Very good');

insert into boey_electionservice.Candidates(name, description, image)
values('Leeroy Jenkins','Always ahead of the others','The best there is');

insert into boey_electionservice.Candidates(name, description, image)
values('Jack Daniel','Can be very disturbing','Worst');

insert into boey_electionservice.Candidates(name, description, image)
values('Personne P. Paul','Always forgotten','Meh');

insert into boey_electionservice.Candidates(name, description, image)
values('Baltaxorus the Third','The third Baltaxorus','Better than nothing');


insert into boey_electionservice.Ballots(title, start, end, isPublic, isAnonymous, ownerId, forumId)
values ('A first taste of a poll','1985-06-16','1985-06-20',true,true,3,1);

insert into boey_electionservice.Ballots(title, start, end, isPublic, isAnonymous, ownerId, forumId)
values ('First VIP poll','2000-09-11','1985-07-10',false,true,7,2);

insert into boey_electionservice.Ballots(title, start, end, isPublic, isAnonymous, ownerId, forumId)
values ('First anonymous poll','2020-01-01','2020-01-30',true,false,1,3);


insert into boey_electionservice.BallotElector (pollId, electorId)
values (1,1);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (1,2);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (1,6);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (1,7);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (1,8);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (2,1);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (2,3);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (2,4);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (2,5);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (2,8);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (2,9);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (2,11);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (3,2);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (3,3);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (3,4);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (3,5);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (3,6);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (3,7);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (3,8);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (3,9);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (3,10);

insert into boey_electionservice.BallotElector (pollId, electorId)
values (3,11);


insert into boey_electionservice.BallotCandidate(pollId, candidateId)
values (1,1);

insert into boey_electionservice.BallotCandidate(pollId, candidateId)
values (1,3);

insert into boey_electionservice.BallotCandidate(pollId, candidateId)
values (1,4);

insert into boey_electionservice.BallotCandidate(pollId, candidateId)
values (2,2);

insert into boey_electionservice.BallotCandidate(pollId, candidateId)
values (2,3);

insert into boey_electionservice.BallotCandidate(pollId, candidateId)
values (2,4);

insert into boey_electionservice.BallotCandidate(pollId, candidateId)
values (2,5);

insert into boey_electionservice.BallotCandidate(pollId, candidateId)
values (3,1);

insert into boey_electionservice.BallotCandidate(pollId, candidateId)
values (3,2);

insert into boey_electionservice.BallotCandidate(pollId, candidateId)
values (3,3);

insert into boey_electionservice.BallotCandidate(pollId, candidateId)
values (3,4);

insert into boey_electionservice.BallotCandidate(pollId, candidateId)
values (3,5);