import { useEffect, useState } from "react";
import ReviewModel from "../../../models/ReviewModel";

export const ReviewListPage = () => {

    const [reviews, setReviews] = useState<ReviewModel[]>([]);
    const [isLoading, setIsLoading] = useState(true);
    const [httpError, setHttpError] = useState(null);

    // Pagination
    const [currentPage, setCurrentPage] = useState(1);
    const [reviewsPerPage] = useState(5);
    const [totalAmountOfReviews, setTotalAmountOfReviews] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const bookId = (window.location.pathname).split('/')[2];

    useEffect(() => {
        const fetchBookReviews = async () => {
            const reviewUrl: string = `http://localhost:8080/api/reviews/search/findByBookId?bookId=${bookId}&page=${currentPage - 1}&size=${reviewsPerPage}`;

            const responseReviews = await fetch(reviewUrl);

            if (!responseReviews.ok) {
                throw new Error("Something went wrong during the fetch of the review for the bookId");
            }

            const responseJson = await responseReviews.json();
            0
            const responseData = responseJson._embedded.reviews;

            setTotalAmountOfReviews(responseJson.page.totalElements);
            setTotalPages(responseJson.page.totalPages);

            const loadedReviews: ReviewModel[] = [];

            for (const key in responseData) {
                loadedReviews.push({
                    id: responseData[key].id,
                    userEmail: responseData[key].userEmail,
                    date: responseData[key].date,
                    rating: responseData[key].rating,
                    book_id: responseData[key].bookId,
                    reviewDescription: responseData[key].reviewDescription,
                });
            }

            // Sort reviews by date in descending order (latest first)
            loadedReviews.sort((a, b) => b.date.localeCompare(a.date));

            setReviews(loadedReviews);
            setIsLoading(false);
        };

        fetchBookReviews().catch((error: any) => {
            setIsLoading(false);
            setHttpError(error.message);
        });
    }, [currentPage]);

    return (
        <div></div>
    );
}