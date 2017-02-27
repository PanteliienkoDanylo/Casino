package softgroup.ua.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softgroup.ua.jpa.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>{


}
