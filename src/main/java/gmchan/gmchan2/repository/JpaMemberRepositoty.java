package gmchan.gmchan2.repository;

import gmchan.gmchan2.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepositoty implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepositoty(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return (List<Member>) em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public void clearStore() {

    }
}
