package com.example.loascheduler.raidGroup.service;

import com.example.loascheduler.character.dto.response.CharacterInfoResponse;
import com.example.loascheduler.character.entity.Characters;
import com.example.loascheduler.character.repository.CharacterRepository;
import com.example.loascheduler.raidGroup.dto.response.RaidGroupListResponse;
import com.example.loascheduler.raidGroup.entity.RaidCharacters;
import com.example.loascheduler.raidGroup.entity.RaidGroup;
import com.example.loascheduler.raidGroup.repository.RaidCharactersRepository;
import com.example.loascheduler.raidGroup.repository.RaidGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RaidGroupService {

    private final RaidGroupRepository raidGroupRepository;
    private final RaidCharactersRepository raidCharactersRepository;
    private final CharacterRepository characterRepository;

    public void addRaidGroup(String day) {
        raidGroupRepository.save(new RaidGroup(day));
    }

    public void setRaidName(Long id, String raidName) {
        RaidGroup raidGroup = raidGroupRepository.findById(id).orElseThrow();
        raidGroup.updateRaidName(raidName);
    }

    @Transactional(readOnly = true)
    public List<RaidGroupListResponse> getRaidGroup(String day) {
        List<RaidGroup> raidGroups = raidGroupRepository.findAllByDayWithCharacters(day);

        return raidGroups.stream().map(raidGroup -> {
            RaidGroupListResponse response = new RaidGroupListResponse();
            response.setRaidGroupId(raidGroup.getId());
            response.setName(raidGroup.getRaidName());
            response.setDay(raidGroup.getDay());

            List<CharacterInfoResponse> characters = raidGroup.getCharacters().stream()
                    .map(raidCharacter -> {
                        CharacterInfoResponse characterResponse = new CharacterInfoResponse(
                                raidCharacter.getCharacter().getNickName(),
                                raidCharacter.getCharacter().getLevel(),
                                raidCharacter.getCharacter().getClassName()
                        );
                        return characterResponse;
                    }).collect(Collectors.toList());

            response.setCharacters(characters);
            return response;
        }).collect(Collectors.toList());
    }

    public void addCharacterToRaidGroup(Long raidGroupId, Long characterId) {
        RaidGroup raidGroup = raidGroupRepository.findById(raidGroupId).orElseThrow();
        Characters character = characterRepository.findById(characterId).orElseThrow();

        RaidCharacters raidCharacters = new RaidCharacters(raidGroup, character);
        raidCharactersRepository.save(raidCharacters);
    }

    public void deleteCharacterToRaidGroup(Long raidGroupId, Long characterId) {
        RaidCharacters raidCharacter = raidCharactersRepository.findByRaidGroupIdAndCharacterId(raidGroupId, characterId);
        raidCharactersRepository.delete(raidCharacter);
    }

    public void deleteRaid(Long raidGroupId) {
        RaidGroup raidGroup = raidGroupRepository.findById(raidGroupId).orElseThrow();
        raidGroupRepository.delete(raidGroup);
    }
}