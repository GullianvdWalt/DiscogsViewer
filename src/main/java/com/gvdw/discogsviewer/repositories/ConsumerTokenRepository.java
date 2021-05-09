package com.gvdw.discogsviewer.repositories;

import com.gvdw.discogsviewer.models.JpaOAuthConsumerToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Gullian Van Der Walt
 * Created at 14:33 on May, 2021
 *
 * DAO for JpaOAuthConsumerToken
 */
@Repository("ConsumerTokenRepository")
public interface ConsumerTokenRepository extends JpaRepository<JpaOAuthConsumerToken, String> {

}
